package ch.datatrans.model;

import ch.datatrans.invoker.JSON;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolymorphicModelsTest {

    @Test
    void discriminatorSelectsExactlyOneMappedModel() {
        assertModel(AliasConvertRequest.class, CardAliasConvertRequest.class,
                "{\"type\":\"CARD\",\"legacyAlias\":\"alias\"}");
        assertModel(TokenizeRequest.class, CardTokenizeRequest.class, "{\"type\":\"CARD\"}");
        assertModel(TokenizeResponse.class, CardTokenizeResponse.class, "{\"type\":\"CARD\"}");
        assertModel(DetokenizeRequest.class, CardDetokenizeRequest.class, "{\"type\":\"CARD\"}");
        assertModel(DetokenizeResponse.class, CardDetokenizeResponse.class, "{\"type\":\"CARD\"}");
        assertModel(Card.class, AliasCard.class, "{\"type\":\"ALIAS\"}");
        assertModel(CardInit.class, AliasInitCard.class, "{\"type\":\"ALIAS\"}");
        assertModel(DccRequest.class, DccAliasCardRequest.class,
                "{\"type\":\"ALIAS\",\"amount\":100,\"currency\":\"CHF\"}");
    }

    private void assertModel(Class<? extends AbstractOpenApiSchema> union, Class<?> expected, String json) {
        AbstractOpenApiSchema model = JSON.getGson().fromJson(json, union);

        assertEquals(expected, model.getActualInstance().getClass());
    }
}
