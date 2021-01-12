package ch.zhaw.mas.sharingAppServer;

import ch.zhaw.mas.sharingAppServer.serverSite.domain.ItemController;
import ch.zhaw.mas.sharingAppServer.serverSite.domain.ItemModel;
import ch.zhaw.mas.sharingAppServer.serverSite.domain.ItemService;
import ch.zhaw.mas.sharingAppServer.serverSite.domain.UserInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
@RunWith(SpringRunner.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    public void getAllItemsTest() throws Exception {
        ItemModel item = new ItemModel();
        item.setName("test item");
        ItemModel item2 = new ItemModel();
        item2.setName("test item 2");
        List<ItemModel> itemList = Arrays.asList(item, item2);

        given(itemService.getAllItems()).willReturn(itemList);

        mockMvc.perform(MockMvcRequestBuilders.get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(itemList.size())))
                .andExpect(jsonPath("$[0].name", is(itemList.get(0).getName())))
                .andExpect(jsonPath("$[1].name", is(itemList.get(1).getName())));
    }

//    @Test
//    public void saveNewItemTest() throws Exception {
//        ItemModel item = new ItemModel();
//        item.setName("test item");
//        mockMvc.perform(MockMvcRequestBuilders.post("/items", item))
//                .andExpect(status().isForbidden());
//
//    }
}
