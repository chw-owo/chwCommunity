//package com.example.chwblog.Integration;
//
//import com.example.chwblog.dto.SignupRequestDto;
//import com.example.chwblog.model.User;
//import com.example.chwblog.service.UserService;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.ui.Model;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class UserIntegrationTest {
//    @Autowired
//    com.example.chwblog.service.UserService UserService;
//
//    Long userId = 100L;
//    User createdUser = null;
//    int updatedMyPrice = -1;
//
//    @Test
//    @Order(1)
//    @DisplayName("신규 관심상품 등록")
//    void test1() {
//// given
//        String username = "";
//        String password = "";
//        SignupRequestDto requestDto = new SignupRequestDto(
//                username,
//                password
//        );
//
//// when
//        //Model model = new Model();
//        //User user = UserService.registerUser(model, requestDto);
//
//// then
//        assertNotNull(user.getId());
//        assertEquals(username, user.getUsername());
//        assertEquals(password, user.getPassword());
//        createdUser = user;
//    }
