package org.bartoszwojcik.investmentportfolioapi.service.user;

import java.math.BigDecimal;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.dto.user.UpdateUserRequestDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.UserDto;
import org.bartoszwojcik.investmentportfolioapi.dto.user.register.RegistrationRequestDto;
import org.bartoszwojcik.investmentportfolioapi.exception.RegistrationException;
import org.bartoszwojcik.investmentportfolioapi.mapper.UserMapper;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Role;
import org.bartoszwojcik.investmentportfolioapi.model.classes.User;
import org.bartoszwojcik.investmentportfolioapi.model.enums.RoleName;
import org.bartoszwojcik.investmentportfolioapi.repository.role.RoleRepository;
import org.bartoszwojcik.investmentportfolioapi.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final RoleName USER = RoleName.ROLE_USER;
    private static final Role role = new Role();
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registration(RegistrationRequestDto requestDto) {
        checkIfUserExists(requestDto);
        User user = userMapper.toUser(requestDto);
        Role getRole = roleRepository.findByName(USER).orElseGet(
                () -> roleRepository.save(role)
        );
        user.setRoles(Set.of(getRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCash(BigDecimal.valueOf(0));
        return userMapper.toDto(
                userRepository.save(user)
        );
    }

    @Override
    public UserDto getMyProfile(User user) {
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateMyProfile(User user, UpdateUserRequestDto request) {
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        return userMapper.toDto(
                userRepository.save(user));
    }

    private void checkIfUserExists(RegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException(
                    "Email address already in use"
            );

        }
    }
}
