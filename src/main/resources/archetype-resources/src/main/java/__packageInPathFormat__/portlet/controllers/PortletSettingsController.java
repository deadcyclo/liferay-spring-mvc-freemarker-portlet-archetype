package ${package}.portlet.controllers;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import ${package}.common.web.freemarker.utils.FreemarkerValueUtil;
import ${package}.portlet.beans.Settings;
import ${package}.portlet.keys.ParameterKeys;
import ${package}.portlet.keys.ActionKeys;
import ${package}.portlet.keys.SettingsKeys;
import ${package}.portlet.keys.MessageKeys;
import ${package}.portlet.keys.ViewKeys;
import ${package}.portlet.keys.PortletActionKeys;
import ${package}.common.web.freemarker.keys.FreemarkerKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.context.PortletConfigAware;

import javax.portlet.*;
import java.io.IOException;

/**
 * controller class for the order portlet settings
 */

@Controller
@RequestMapping(PortletActionKeys.EDIT)
public class PortletSettingsController implements PortletConfigAware {
    private static final Log LOG = LogFactoryUtil.getLog(PortletSettingsController.class);

    private PortletConfig portletConfig;

    @Autowired
    FreemarkerValueUtil freemarkerValueUtil;

    /**
     * Render the settings page
     *
     * @param request spring mvc render request
     * @param model spring mvc model object
     */
    @RenderMapping
    public String handleRenderRequest(RenderRequest request, Model model)  {
        if (!model.containsAttribute(FreemarkerKeys.LANGUAGEUTIL))
            freemarkerValueUtil.addLanguageUtils(request, model);
        PortletPreferences preferences = request.getPreferences();
        boolean randomSetting = Boolean.valueOf(preferences.getValue(SettingsKeys.RANDOM_SETTING, "false"));
        model.addAttribute(ParameterKeys.SETTINGS, new Settings(randomSetting));
        return ViewKeys.SETTINGS;
    }

    /**
     * Action for setting the portlet settings
     *
     * @param model Spring mvc model object
     * @param settings model attribute containing the settings from the form
     * @param request Spring mvc action request
     */
    @ActionMapping(value = ActionKeys.SETTINGS)
    public void addSettings(Model model, @ModelAttribute(ParameterKeys.SETTINGS) Settings settings,
                            ActionRequest request) throws ReadOnlyException, IOException, ValidatorException {
        PortletPreferences preferences = request.getPreferences();
        preferences.setValue(SettingsKeys.RANDOM_SETTING, String.valueOf(settings.isRandomSetting()));
        preferences.store();
        SessionMessages.add(request, MessageKeys.SETTINGS_SAVED);
    }

    /**
     * Create Spring MVC model attribute that retains information on the demo portlet settings
     *
     * @return new empty order portlet settings object
     */
    @ModelAttribute(ParameterKeys.SETTINGS)
    public Settings createSettings() {
        return new Settings();
    }

    /**
     * Ensure we get access to the portlet config object
     *
     * @param portletConfig
     */
    @Override
    public void setPortletConfig(PortletConfig portletConfig) {
        this.portletConfig = portletConfig;
    }

}
