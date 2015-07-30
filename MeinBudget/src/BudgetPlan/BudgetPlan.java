package BudgetPlan;
import java.awt.EventQueue;
import Anmelden.AnmeldenFenster;

/**
 * Anwendung BudgetPlan
 * 
 */

public class BudgetPlan {
	public static void main(String[] args) {
		//Anmeldung
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AnmeldenFenster frame = new AnmeldenFenster();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
		
		/*BudgetPlanModel budget = new BudgetPlanModel(); // Modell
		new BudgetPlanGUI(budget); // View und Controller
		*/
		
	}

}
