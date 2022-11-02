package step.learning.services;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

import javax.inject.Named;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        // Будуть питати RandomProvider - видати RandomMax
        bind( RandomProvider.class )      // bind - зв'язування
                .to( RandomMax.class ) ;  // інтерфейса та класу

        // bind( SymbolService.class ).to( CharService.class ) ;
        bind( SymbolService.class )
                .annotatedWith( Names.named( "CharProvider" ) )
                .to( CharService.class ) ;
        bind( SymbolService.class )
                .annotatedWith( Names.named( "DigitProvider" ) )
                .to( DigitService.class ) ;
    }

    // альтернатива bind - метод постачальник заданого типу (String) та імені
    @Provides
    @Named("MS-SQL")
    String msConnectionString() {
        return new String("MS Connection String" ) ;
    }
    @Provides
    @Named("Oracle-SQL")
    String oraclesConnectionString() {
        return "Oracle Connection String" ;
    }
}