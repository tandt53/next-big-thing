package tandt.web.customguice;

import com.google.inject.AbstractModule;
import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import tandt.common.configurations.Context;
import tandt.common.configurations.ContextImpl;
import tandt.common.configurations.capability.Capability;

import java.lang.reflect.Field;

public class PropsModule extends AbstractModule {

    private Context service;
    private Capability capability;

    public PropsModule() {
        this.service = ContextImpl.createInstance();
        capability = service.getCapability();
    }

    @Override
    protected void configure() {
        bindListener(Matchers.any(), new TypeListener() {
            @Override
            public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
                Class<? super I> clazz = type.getRawType();

                for (Field field : clazz.getDeclaredFields()) {
                    Prop prop = field.getAnnotation(Prop.class);
                    if (prop == null)
                        continue;

                    encounter.register(new PropInjector<I>(prop, field));
                }
            }
        });
    }

    class PropInjector<I> implements MembersInjector<I> {

        private final Prop prop;
        private final Field field;

        PropInjector(Prop prop, Field field) {
            this.prop = prop;
            this.field = field;
            field.setAccessible(true);
        }


        @Override
        public void injectMembers(I instance) {
            try {
                Class<?> targetType = field.getType();
                Object val = capability.get(prop.value());
                field.set(instance, val);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }


    }
}