package com.giveandgo.association.security;

import com.giveandgo.association.entities.Utilisateur;
import com.giveandgo.association.service.UtilisateurService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurService utilisateurService;

    public CustomUserDetailsService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.findByEmail(email);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Aucun utilisateur non trouvé avec l'email : " + email);
        }

        // Créer les autorités (rôles + permissions supplémentaires)
        Collection<GrantedAuthority> authorities = new ArrayList<>(utilisateur.getAuthorities());

        // Ajouter des permissions granulaires en fonction du rôle
        switch (utilisateur.getRole()) {
            case "ADMIN":
                authorities.add(new SimpleGrantedAuthority("CAN_EDIT_USERS"));
                authorities.add(new SimpleGrantedAuthority("CAN_DELETE_USERS"));
                authorities.add(new SimpleGrantedAuthority("CAN_VIEW_REPORTS"));
                authorities.add(new SimpleGrantedAuthority("CAN_EDIT_MISSIONS"));
                break;
            case "MEMBRE":
                authorities.add(new SimpleGrantedAuthority("CAN_VIEW_MISSIONS"));
                authorities.add(new SimpleGrantedAuthority("CAN_PARTICIPATE_MISSIONS"));
                break;
            case "BENEVOLE":
                authorities.add(new SimpleGrantedAuthority("CAN_VIEW_MISSIONS"));
                authorities.add(new SimpleGrantedAuthority("CAN_LOG_HOURS"));
                break;
            default:
                break;
        }

        return User.builder()
                .username(utilisateur.getEmail())
                .password(utilisateur.getPassword())
                .authorities(authorities)
                .build();
    }
}