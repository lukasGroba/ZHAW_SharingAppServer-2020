package ch.zhaw.mas.sharingAppServer;

import ch.zhaw.mas.sharingAppServer.serverSite.domain.ItemController;
import ch.zhaw.mas.sharingAppServer.serverSite.domain.UserController;
import ch.zhaw.mas.sharingAppServer.serverSite.domain.UserModel;
import ch.zhaw.mas.sharingAppServer.serverSite.domain.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserService userService;

    @Test
    public void getUsersTest() throws Exception {
        UserModel user = new UserModel();
        user.setUsername("test name");
        List<UserModel> users = Arrays.asList(user);

        given(userService.getAllUsers()).willReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
