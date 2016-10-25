package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userrepo.findOneByEmail(username);
		if (user == null) {
			return null;
		}
		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(Role.USER.name());
		if (Role.ADMIN.name().equals(user.getRole().name())) {
			System.out.println(Role.ADMIN);
			auth = AuthorityUtils.commaSeparatedStringToAuthorityList(Role.ADMIN.name());
		}
		String password = user.getPasswordHash();
		String Email = user.getEmail();
		System.out.println(password + " " + Email +"  "+user.getRole());
		return new org.springframework.security.core.userdetails.User(Email, password, auth);
	}

}