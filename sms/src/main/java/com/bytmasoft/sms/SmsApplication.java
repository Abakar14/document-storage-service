package com.bytmasoft.sms;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.bytmasoft.sms.view.FxmlView;
import com.bytmasoft.sms.view.StageManager;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;

public class SmsApplication  extends Application {

	private ConfigurableApplicationContext applicationContext;
	protected StageManager stageManager;
	
	@Override
	public void init() throws Exception {
		
	ApplicationContextInitializer<GenericApplicationContext> initializer = 
			
			ac -> {		
					ac.registerBean(Application.class, () -> SmsApplication.this);			
					ac.registerBean(Parameters.class, this::getParameters);
					ac.registerBean(HostServices.class, this::getHostServices);				
				
			};
			
	this.applicationContext = new SpringApplicationBuilder()
			.sources(BootFxApplication.class)
			.initializers(initializer )
			.run(getParameters().getRaw().toArray(new String [0]));
	}
	

	@Override
	public void start(Stage stage) throws Exception {
//		stage.getModality();
		this.stageManager = applicationContext.getBean(StageManager.class, stage);
		displayInitialScene();
	}
	
	
	protected void displayInitialScene() {
		this.stageManager.switchScene(FxmlView.LOGIN);
		
	}


//	@Override
//	public void stop() throws Exception {
//		this.applicationContext.close();
//		Platform.exit();
//	}


}
