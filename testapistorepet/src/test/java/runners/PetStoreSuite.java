package runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NewPetStore.class,
        GetPetStore.class,
        DeletePetStore.class
})
public class PetStoreSuite {
}
