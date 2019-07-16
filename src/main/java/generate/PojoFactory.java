package generate;

import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.IOException;

public class PojoFactory {

    public static void generateSchema(String schema) throws IOException {

        JCodeModel codeModel = new JCodeModel();

        GenerationConfig config = new DefaultGenerationConfig(){
            @Override
            public boolean isIncludeToString() {
                return false;
            }

            @Override
            public boolean isIncludeAdditionalProperties() {
                return false;
            }

            @Override
            public boolean isIncludeJsr303Annotations() {
                return true;
            }

            @Override
            public boolean isGenerateBuilders() {
                return true;
            }
        };

        RuleFactory ruleFactory = new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore());
        SchemaMapper schemaMapper = new SchemaMapper(ruleFactory, new SchemaGenerator());
        schemaMapper.generate(codeModel, "blah", "blah.package", schema);
        codeModel.build(new File("/c/temp"));
    }
}
