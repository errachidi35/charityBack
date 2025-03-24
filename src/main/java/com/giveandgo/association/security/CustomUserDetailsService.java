package com.giveandgo.association.security;

import com.giveandgo.association.model.Utilisateur;
import com.giveandgo.association.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.findByEmail(email);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Aucun utilisateur non trouvé avec l'email : " + email);
        }
        return User.builder()
                .username(utilisateur.getEmail())
                .password(utilisateur.getMotDePasse())
                .roles(utilisateur.getRole())
                .build();
    }
}