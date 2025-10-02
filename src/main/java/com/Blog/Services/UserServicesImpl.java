package com.Blog.Services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Entity.User;
import com.Blog.Exception.ResourceNotFoundException;
import com.Blog.Helper.UserDto;
import com.Blog.Repository.UserRepository;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        String id = UUID.randomUUID().toString();
        userDto.setId(id);
        User user = dtoToUser(userDto);
        User sUser = this.userRepository.save(user);
        return this.userToDto(sUser);

    }

    @Override
    public void deleteUser(String id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        this.userRepository.delete(user);

    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> list = this.userRepository.findAll();
        List<UserDto> userDtos = list.stream().map((user) -> userToDto(user)).collect(Collectors.toList());
        return userDtos;

    }

    @Override
    public UserDto getUserById(String id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return this.userToDto(user);

    }

    @Override
    public UserDto updateUser(UserDto userdto, String id) {

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found in Database"));
        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());
        user.setAbout(userdto.getAbout());
        user.setPassword(userdto.getPassword());
        User sUser = this.userRepository.save(user);
        return this.userToDto(sUser);

    }

    public User dtoToUser(UserDto userDto) {
        // User user=new User();
        // user.setId(userDto.getId());
        // user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        // user.setPassword(userDto.getPassword());
        // user.setAbout(userDto.getAbout());

        User user = this.modelMapper.map(userDto, User.class);
        return user;

    }

    public UserDto userToDto(User user) {
        // UserDto userDto=new UserDto();
        // userDto.setId(user.getId());
        // userDto.setName(user.getName());
        // userDto.setEmail(user.getEmail());
        // userDto.setPassword(user.getPassword());
        // userDto.setAbout(user.getAbout());

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
