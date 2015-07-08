package hudson.plugins.analysis.audit;

import hudson.model.Action;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

/**
 * A project action displays a link on the side panel of a project.
 *
 * @param
 *
 * @author William Wu
 */
@ExportedBean
public abstract class AbstractAuditAction implements Action {

    @Override
    public String getIconFileName() {
        return "testAudit";
    }

    @Override @Exported
    public String getDisplayName() {
        return "testAudit";
    }

    @Override
    public String getUrlName() {
        return "testAudit";
    }

    public String getCloudUrl(){
        return "testAudit";
    }
}
