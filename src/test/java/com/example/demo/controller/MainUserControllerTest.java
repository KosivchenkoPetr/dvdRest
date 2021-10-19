package com.example.demo.controller;

import com.example.demo.beans.Credential;
import com.example.demo.beans.Disk;
import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import com.example.demo.service.impl.DiskServiceImpl;
import com.example.demo.service.impl.TakenItemServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(MainUserController.class)
@RunWith(SpringRunner.class)
@WebMvcTest(MainUserController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes={MainUserController.class})
class MainUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserServiceImpl userService;

    @MockBean
    TakenItemServiceImpl takenItemService;

    @MockBean
    DiskServiceImpl diskService;

    @MockBean
    UserDetailsService userDetailsService;


    @Test
    @WithMockUser(username = "user", password = "user", roles={"USER"})
    void welcome() throws Exception {

        User user = new User("User");

        Credential crUser = new Credential("user", "user", "USER");
        user.setCredential(crUser);
        Disk d = new Disk("Disk1");
        Disk d3 = new Disk("Disk3");
        TakenItem takenItem = new TakenItem();
        TakenItem takenItem2 = new TakenItem();

        takenItem.setDisk(d);
        takenItem2.setDisk(d3);

        List<TakenItem> listTakenItems = new ArrayList<>();
        listTakenItems.add(takenItem);
        listTakenItems.add(takenItem2);
        when(takenItemService.getAllTakenItems()).thenReturn(listTakenItems);


        String url = "/user/diskSharing/all/takenItem";
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
    }
}