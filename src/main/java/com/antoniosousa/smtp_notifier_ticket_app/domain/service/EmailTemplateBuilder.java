package com.antoniosousa.smtp_notifier_ticket_app.domain.service;

import org.springframework.stereotype.Component;

@Component
public class EmailTemplateBuilder {

    public String buildWelcomeEmailContent(String userName) {
        return """
            <html>
                <body style="font-family: Arial, sans-serif; padding: 20px;">
                    <h2 style="color: #4CAF50;">Bem-vindo à nossa plataforma de eventos, %s!</h2>
                    <p>Olá %s,</p>
                    <p>Estamos muito felizes em ter você conosco! Nossa plataforma oferece uma ampla variedade de eventos que você pode explorar e encontrar aquele que mais desperta o seu interesse.</p>
                    
                    <p style="margin-top: 20px;">Aqui estão alguns eventos que podem te interessar:</p>
                    <ul style="list-style-type: none; padding: 0;">
                        <li style="margin: 10px 0;">
                            <span style="color: #4CAF50;">🎤</span> Festivais de música ao vivo com ingressos disponíveis!
                        </li>
                        <li style="margin: 10px 0;">
                            <span style="color: #4CAF50;">🏀</span> Jogos esportivos imperdíveis para os amantes do esporte.
                        </li>
                        <li style="margin: 10px 0;">
                            <span style="color: #4CAF50;">🎭</span> Peças de teatro, shows de comédia e muito mais!
                        </li>
                    </ul>
                    
                    <p style="margin-top: 20px;">Você pode comprar seus ingressos de forma rápida e segura diretamente pela nossa plataforma.</p>
                    <p style="margin-top: 30px;">Estamos prontos para tornar sua experiência incrível. Se tiver alguma dúvida, entre em contato com a nossa equipe!</p>
                    <p style="color: #555;">Atenciosamente,<br>Equipe de Suporte</p>
                </body>
            </html>
        """.formatted(userName, userName);
    }
}
