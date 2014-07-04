package com.jprogrammers.filter;

import org.primefaces.component.fileupload.FileUploadRenderer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Created by EN20 on 7/3/14.
 */
public class UploadRenderer extends FileUploadRenderer {

    @Override
    public void decode(FacesContext context, UIComponent component) {
        if (context.getExternalContext().getRequestContentType().toLowerCase().startsWith("multipart/")) {
            super.decode(context, component);
        }
    }

}
