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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainUserController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {MainUserController.class})
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
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void getAllCollectionTakenItems() throws Exception {

        User user = new User("User");
        user.setId(1);

        Credential crUser = new Credential("user", "user", "USER");
        user.setCredential(crUser);
        crUser.setUser(user);

        Disk d = new Disk("Disk1");
        d.setId(22L);
        Disk d2 = new Disk("Disk2");
        d2.setId(33L);

        TakenItem takenItem = new TakenItem();
        TakenItem takenItem2 = new TakenItem();

        takenItem.setDisk(d);
        takenItem.setId(1L);
        takenItem.setCurrentOwner(user);

        takenItem2.setDisk(d2);
        takenItem2.setId(2L);
        takenItem2.setCurrentOwner(user);

        List<TakenItem> listTakenItems = new ArrayList<>();
        listTakenItems.add(takenItem);
        listTakenItems.add(takenItem2);
        when(takenItemService.getAllTakenItems()).thenReturn(listTakenItems);
        when(userService.getUser(user.getId())).thenReturn(user);
        when(userService.findCredentialByName("user")).thenReturn(crUser);

        String url = "/user/diskSharing/all/takenItem";
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].disk.id").value("22"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].disk.name").value("Disk1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currentOwner.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currentOwner.name").value("User"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].disk.id").value("33"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].disk.name").value("Disk2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].currentOwner.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].currentOwner.name").value("User"))

                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void getAllTakenItemsOfCurrentOwner() throws Exception {

        User user = new User("User");
        user.setId(1);

        Credential crUser = new Credential("user", "user", "USER");
        user.setCredential(crUser);
        crUser.setUser(user);

        Disk d = new Disk("Disk1");
        d.setId(22L);
        Disk d2 = new Disk("Disk2");
        d2.setId(33L);

        TakenItem takenItem = new TakenItem();
        TakenItem takenItem2 = new TakenItem();

        takenItem.setDisk(d);
        takenItem.setId(1L);
        takenItem.setCurrentOwner(user);

        takenItem2.setDisk(d2);
        takenItem2.setId(2L);
        takenItem2.setCurrentOwner(user);

        List<TakenItem> listTakenItems = new ArrayList<>();
        listTakenItems.add(takenItem);
        listTakenItems.add(takenItem2);
        when(takenItemService.getAllTakenItemsOfCurrentOwner(1L)).thenReturn(listTakenItems);
        when(userService.getUser(user.getId())).thenReturn(user);
        when(userService.findCredentialByName("user")).thenReturn(crUser);

        String url = "/user/diskSharing/currentOwner";
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].disk.id").value("22"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].disk.name").value("Disk1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currentOwner.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currentOwner.name").value("User"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].disk.id").value("33"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].disk.name").value("Disk2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].currentOwner.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].currentOwner.name").value("User"))

                .andExpect(status().isOk());
    }
}