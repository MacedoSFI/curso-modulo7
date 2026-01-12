package br.com.java_spring.backend.config;

import br.com.java_spring.backend.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository; // Precisamos do repositório para carregar o usuário

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Recupera o token do cabeçalho da requisição
        var token = recuperarToken(request);

        if (token != null) {
            // 2. Valida o token e extrai o login do usuário (subject)
            var login = tokenService.validarToken(token);

            // 3. Busca o usuário no banco de dados
            UserDetails usuario = usuarioRepository.findByLogin(login);
            if (usuario != null) {
                // 4. A LINHA QUE FALTAVA: Cria o objeto de autenticação do Spring
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                // 5. Autentica oficialmente o usuário no contexto do Spring para ESTA requisição
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("Atenção: Token válido, mas login '" + login + "' não existe no banco.");
            }

        }

        // 6. Continua o fluxo para o próximo filtro ou para o Controller
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}