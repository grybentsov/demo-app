package com.example.app;

import com.example.app.entities.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Role> json;

    @Test
    public void testSerialize() throws Exception {
        Role role = new Role();
        role.setId(1);
        role.setName("CLIENT");
        assertThat(this.json.write(role)).hasJsonPathNumberValue("$.id");
        assertThat(this.json.write(role)).extractingJsonPathStringValue("$.name").isEqualTo("CLIENT");
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"id\": 2,\"name\":\"ADMIN\"}";
        Role realRole = new Role();
        realRole.setId(2);
        realRole.setName("ADMIN");

        assertThat(this.json.parse(content)).isEqualTo(realRole);
        assertThat(this.json.parseObject(content).getName()).isEqualTo("ADMIN");
    }
}