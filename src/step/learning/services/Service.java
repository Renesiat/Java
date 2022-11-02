package step.learning.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.inject.Named;

@Singleton
public class Service {
    @Inject
    private RandomProvider randomProvider ;
    @Inject @Named("CharProvider")
    private SymbolService symbolService ;

    public String getString() {
        return String.format( "Inside Service [rnd: %d, sym: %c]",
                randomProvider.getN(),
                symbolService.getSymbol() ) ;
    }
}