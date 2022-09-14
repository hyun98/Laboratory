package businessruleengine;

import com.software.businessruleengine.Action;
import com.software.businessruleengine.BusinessRuleEngine;
import com.software.businessruleengine.Facts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BusinessRuleEngineBasicTests {
//
//    @Test
//    void shouldHaveNoRulesInitially() {
//        final Facts mockFacts = mock(Facts.class);
//        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);
//
//        assertEquals(0, businessRuleEngine.count());
//    }
//
//    @Test
//    void shouldAddTwoActions() {
//        final Facts mockFacts = mock(Facts.class);
//        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);
//        
//        businessRuleEngine.addAction((fact) -> {});
//        businessRuleEngine.addAction((fact) -> {});
//        
//        assertEquals(2, businessRuleEngine.count());
//    }
//
//    @Test
//    void shouldExecuteOneAction() {
//        final Action mockAction = mock(Action.class);
//        final Facts mockFacts = mock(Facts.class);
//        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);
//
//        businessRuleEngine.addAction(mockAction);
//        businessRuleEngine.run();
//
//        verify(mockAction).execute(mockFacts);
//    }
}
