package com.cricophile.stats.service.impl;

import com.cricophile.stats.entity.User;
import com.cricophile.stats.exception.ResourceNotFound;
import com.cricophile.stats.payload.UserDto;
import com.cricophile.stats.repository.UserRepo;
import com.cricophile.stats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow( () -> new ResourceNotFound("User", " Id " , userId));

        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        UserDto updatedUserDto = this.userToDto(updatedUser);

        return updatedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow( () -> new ResourceNotFound("User", " Id " , userId));

        UserDto userDto = this.userToDto(user);

        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> userList = this.userRepo.findAll();

        List<UserDto> userDtoList = userList.stream()
                                            .map( user -> this.userToDto(user))
                                            .collect(Collectors.toList());

        return userDtoList;
    }

    @Override
    public void deleteUserById(Long userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow( () -> new ResourceNotFound("User", " Id " , userId));

        this.userRepo.delete(user);

    }

    private User dtoToUser(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        return  user;
    }

    private UserDto userToDto(User user){

        UserDto userDto = new UserDto();

        userDto.setUserName(user.getUserName());
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());

        return userDto;
    }
}
