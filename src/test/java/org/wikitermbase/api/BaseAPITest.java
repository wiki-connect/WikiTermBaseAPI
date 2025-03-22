package org.wikitermbase.api;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wikitermbase.api.DataModel.Group;
import org.wikitermbase.api.DataModel.Occurence;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BaseAPITest {

    @InjectMocks
    private BaseAPI baseAPI;

    @BeforeEach
    void setup() {
        baseAPI = Mockito.spy(new BaseAPI());
    }

    @Test
    void testSearchAggregated_Success() throws IOException, JSONException {
        String mockJson = "{ \"groups\": [ { \"arabic_normalised\": \"كلمة\", \"dictionary_ids\": [1, 2], \"english_normalised\": \"word\", \"french_normalised\": \"mot\", \"occurences\": [], \"total_relevance\": 0.9 } ] }";
        doReturn(mockJson).when(baseAPI).executeRequest(anyString());

        Group[] result = baseAPI.searchAggregated("test");

        assertNotNull(result);
        assertEquals(1, result.length);
        assertEquals("كلمة", result[0].getArabicNormalised());
    }

    @Test
    void testSearch_Success() throws IOException, JSONException {
        String mockJson = "{ \"results\": [ { \"arabic\": \"كلمة\", \"english\": \"word\", \"id\": 1 } ] }";
        doReturn(mockJson).when(baseAPI).executeRequest(anyString());

        Occurence[] result = baseAPI.search("test");

        assertNotNull(result);
        assertEquals(1, result.length);
        assertEquals("كلمة", result[0].getArabic());
    }

    @Test
    void testSearchAggregated_IOException() throws IOException {
        doThrow(new IOException("Network error")).when(baseAPI).executeRequest(anyString());

        assertThrows(IOException.class, () -> baseAPI.searchAggregated("test"));
    }

    @Test
    void testSearchAggregated_JSONException() throws IOException {
        doReturn("Invalid JSON").when(baseAPI).executeRequest(anyString());

        assertThrows(JSONException.class, () -> baseAPI.searchAggregated("test"));
    }
}