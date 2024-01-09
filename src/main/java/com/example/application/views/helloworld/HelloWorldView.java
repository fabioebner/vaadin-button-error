package com.example.application.views.helloworld;

import com.example.application.ConferenciaMatriculaService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.HashMap;
import java.util.Map;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout {

    private Button btnFinish = new Button("Click");
    private ProgressBar progress = new ProgressBar();
    private final ConferenciaMatriculaService conferenciaMatriculaService;
    public HelloWorldView(ConferenciaMatriculaService conferenciaMatriculaService) {
        this.conferenciaMatriculaService = conferenciaMatriculaService;
        btnFinish.addClickListener(this::btnFinishClickListener);
        btnFinish.setDisableOnClick(true);
        progress.setIndeterminate(true);
        progress.setVisible(false);

        setMargin(true);

        add(btnFinish, progress);
    }

    private void btnFinishClickListener(ClickEvent<Button> buttonClickEvent) {

        progress.setVisible(true);
        btnFinish.setVisible(false);
        UI ui = UI.getCurrent();
        Map<String, String> itensMatricula = new HashMap<>();
        conferenciaMatriculaService.longRunningTask("2323", itensMatricula).whenComplete((a, b)->{
            if(b != null){
                ui.access(() -> {
                    btnFinish.setEnabled(true);
                    btnFinish.setVisible(true);
                    progress.setVisible(false);
                    Notification.show("Error: " + b.getMessage());

                });
            }else {
                ui.access(() -> {
                    btnFinish.setEnabled(true);
                    System.out.println("Verify button: " + btnFinish.isEnabled());
                    btnFinish.setVisible(true);
                    progress.setVisible(false);
                    Notification.show("Success");
                });
            }
        });
    }

}
