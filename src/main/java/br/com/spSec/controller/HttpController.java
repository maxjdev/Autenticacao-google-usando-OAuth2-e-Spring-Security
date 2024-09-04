package br.com.spSec.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {
    @GetMapping("/public")
    String publicRoute() {
        return "<h1><b><i>PUBLIC  ROUTE</i></b></h1>";
    }

    @GetMapping("/private")
    String privateRoute() {
        return "<h1><b><i>PRIVATE  ROUTE</i></b></h1>";
    }

    @GetMapping("/cookie")
    String cookie(@AuthenticationPrincipal OidcUser principal) {
        return String.format("""
						<h1><b><i>PRIVATE  ROUTE</i></b></h1>
						<h3>Principal: %s</h3><br>
						<h3>E-mail attribute: %s</h3><br>
						<h3>Authorities: %s</h3><br>
						<h3>JWT: %s</h3>
						
						""", principal, principal.getAttribute("email"), principal.getAuthorities(),
                principal.getIdToken().getTokenValue());
    }

    @GetMapping("/jwt")
    String jwt(@AuthenticationPrincipal Jwt jwt) {
        return String.format("""
				<h1>JWT</h1>
				<h3>Principal: %s</h3><br>
				<h3>E-mail attribute: %s</h3><br>
				""", jwt.getClaims(), jwt.getClaim("email"), jwt.getTokenValue());
    }
}