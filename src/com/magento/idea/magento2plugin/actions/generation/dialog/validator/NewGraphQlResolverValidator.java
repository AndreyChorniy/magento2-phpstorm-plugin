/*
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */

package com.magento.idea.magento2plugin.actions.generation.dialog.validator;

import com.jetbrains.php.refactoring.PhpNameUtil;
import com.magento.idea.magento2plugin.actions.generation.dialog.NewGraphQlResolverDialog;
import com.magento.idea.magento2plugin.bundles.CommonBundle;
import com.magento.idea.magento2plugin.bundles.ValidatorBundle;
import com.magento.idea.magento2plugin.util.RegExUtil;
import javax.swing.JOptionPane;

@SuppressWarnings({"PMD.OnlyOneReturn"})
public class NewGraphQlResolverValidator {
    private static NewGraphQlResolverValidator INSTANCE = null;
    private ValidatorBundle validatorBundle;
    private CommonBundle commonBundle;
    private NewGraphQlResolverDialog dialog;

    /**
     * Get instance of a class.
     *
     * @param dialog New GraphQl resolver dialog
     *
     * @return NewGraphQlResolverValidator
     */
    public static NewGraphQlResolverValidator getInstance(NewGraphQlResolverDialog dialog) {
        if (null == INSTANCE) {
            INSTANCE = new NewGraphQlResolverValidator();
        }
        INSTANCE.dialog = dialog;
        return INSTANCE;
    }

    /**
     * New Graph Ql Resolver validator constructor.
     */
    public NewGraphQlResolverValidator() {
        this.validatorBundle = new ValidatorBundle();
        this.commonBundle = new CommonBundle();
    }

    /**
     * Validate whenever new graph Ql resolver dialog data is ready for generation.
     *
     * @return Boolean
     */
    public boolean validate() {
        String errorTitle = commonBundle.message("common.error");
        String resolverClassName = dialog.getGraphQlResolverClassName();

        if (!PhpNameUtil.isValidClassName(resolverClassName)) {
            String errorMessage = this.validatorBundle.message(
                    "validator.class.isNotValid",
                    "GraphQL Resolver Name"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    errorTitle,
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        if (resolverClassName.length() == 0) {
            String errorMessage = validatorBundle.message(
                    "validator.notEmpty",
                    "GraphQL Resolver Name"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    errorTitle,
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        if (!resolverClassName.matches(RegExUtil.ALPHANUMERIC)) {
            String errorMessage = validatorBundle.message(
                    "validator.alphaNumericCharacters",
                    "GraphQL Resolver Name"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    errorTitle,
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        if (!Character.isUpperCase(resolverClassName.charAt(0))
                && !Character.isDigit(resolverClassName.charAt(0))
        ) {
            String errorMessage = validatorBundle.message(
                    "validator.startWithNumberOrCapitalLetter",
                    "GraphQL Resolver Name");
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    errorTitle,
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        String graphQlResolverDirectory = dialog.getGraphQlResolverDirectory();
        if (graphQlResolverDirectory.length() == 0) {
            String errorMessage = validatorBundle.message(
                    "validator.notEmpty",
                    "GraphQL Resolver Directory"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    errorTitle,
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        if (!graphQlResolverDirectory.matches(RegExUtil.DIRECTORY)) {
            String errorMessage = validatorBundle.message(
                    "validator.directory.isNotValid",
                    "GraphQL Resolver Directory"
            );
            JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    errorTitle,
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }
}
