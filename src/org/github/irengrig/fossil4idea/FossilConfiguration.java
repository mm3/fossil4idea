package org.github.irengrig.fossil4idea;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Irina.Chernushina
 * Date: 2/12/13
 * Time: 12:04 PM
 */
@State(
    name = "FossilConfiguration",
    roamingType = RoamingType.DISABLED,
    storages = {
        @Storage(file = StoragePathMacros.WORKSPACE_FILE)
    }
)
public class FossilConfiguration implements PersistentStateComponent<Element> {
  public String FOSSIL_PATH = "";
  private final Map<File, String> myRemoteUrls = new HashMap<File, String>();

  @Nullable
  public Element getState() {
    Element element = new Element("state");
    element.setAttribute("FOSSIL_PATH", FOSSIL_PATH);
    return element;
  }

  public void loadState(Element element) {
    final Attribute fossilPath = element.getAttribute("FOSSIL_PATH");
    if (fossilPath != null) {
      FOSSIL_PATH = fossilPath.getValue();
    }
  }

  public static FossilConfiguration getInstance(final Project project) {
    return ServiceManager.getService(project, FossilConfiguration.class);
  }

  public void setRemoteUrls(final Map<File, String> urls) {
    myRemoteUrls.clear();
    myRemoteUrls.putAll(urls);
  }

  public Map<File, String> getRemoteUrls() {
    return myRemoteUrls;
  }
}
