/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2plugin.actions.generation.generator;

import com.magento.idea.magento2plugin.actions.generation.data.SaveEntityCommandData;
import com.magento.idea.magento2plugin.magento.files.commands.SaveEntityCommandFile;

public class SaveEntityCommandGeneratorTest extends BaseGeneratorTestCase {
    private static final String MODULE_NAME = "Foo_Bar";
    private static final String ENTITY_NAME = "Book";
    private static final String EXPECTED_DIRECTORY = "src/app/code/Foo/Bar/Command/" + ENTITY_NAME;
    private static final String NAMESPACE = "Foo\\Bar\\Command\\" + ENTITY_NAME;
    private static final String CLASS_FQN = NAMESPACE + "\\" + SaveEntityCommandFile.CLASS_NAME;
    private static final String DTO_CLASS_FQN = "Foo\\Bar\\Data\\" + ENTITY_NAME + "Data";
    private static final String MODEL_CLASS_FQN = "Foo\\Bar\\Model\\" + ENTITY_NAME + "Model";
    private static final String RESOURCE_CLASS_FQN = "Foo\\Bar\\Model\\ResourceModel\\"
            + ENTITY_NAME + "Resource";

    /**
     * Test generation of SaveCommand model for entity.
     */
    public void testGenerateSaveEntityCommandFile() {
        final SaveEntityCommandData saveEntityCommandData = new SaveEntityCommandData(
                MODULE_NAME,
                ENTITY_NAME,
                NAMESPACE,
                CLASS_FQN,
                MODEL_CLASS_FQN,
                RESOURCE_CLASS_FQN,
                DTO_CLASS_FQN
        );
        final SaveEntityCommandGenerator saveEntityCommandGenerator =
                new SaveEntityCommandGenerator(
                        saveEntityCommandData,
                        myFixture.getProject(),
                        false
                );
        final String filePath = this.getFixturePath(
                new SaveEntityCommandFile().getFileName()
        );

        assertGeneratedFileIsCorrect(
                myFixture.configureByFile(filePath),
                EXPECTED_DIRECTORY,
                saveEntityCommandGenerator.generate("test")
        );
    }
}
