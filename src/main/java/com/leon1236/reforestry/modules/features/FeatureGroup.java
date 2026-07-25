package com.leon1236.reforestry.modules.features;

import java.util.List;
import java.util.function.BiFunction;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.core.IFeatureSubtype;

public abstract class FeatureGroup<S extends IFeatureSubtype, F extends ModFeature> {
    private final ImmutableMap<S, F> featureByType;

    protected FeatureGroup(ImmutableMap<S, F> featureByType) {
        this.featureByType = featureByType;
    }

    public F get(S subtype) {
        F feature = featureByType.get(subtype);
        if (feature == null) {
            throw new IllegalArgumentException("No feature registered for subtype: " + subtype);
        }
        return feature;
    }

    public ImmutableMap<S, F> getAll() {
        return featureByType;
    }

    public enum IdentifierType implements BiFunction<String, String, String> {
        TYPE_ONLY {
            @Override
            public String apply(String feature, String type) {
                return type;
            }
        },
        PREFIX {
            @Override
            public String apply(String feature, String type) {
                return feature + '_' + type;
            }
        },
        SUFFIX {
            @Override
            public String apply(String feature, String type) {
                return type + '_' + feature;
            }
        }
    }

    public abstract static class Builder<S extends IFeatureSubtype, SELF extends Builder<S, SELF>> {
        protected final Identifier moduleId;
        protected List<S> subtypes = List.of();
        protected String identifier = "";
        protected IdentifierType identifierType = IdentifierType.TYPE_ONLY;

        protected Builder(Identifier moduleId) {
            this.moduleId = moduleId;
        }

        @SuppressWarnings("unchecked")
        protected final SELF self() {
            return (SELF) this;
        }

        public SELF types(S[] types) {
            this.subtypes = List.of(types);
            return self();
        }

        public SELF identifier(String identifier) {
            return identifier(identifier, IdentifierType.PREFIX);
        }

        public SELF identifier(String identifier, IdentifierType type) {
            this.identifier = identifier;
            this.identifierType = type;
            return self();
        }

        protected String getRegistryName(S subtype) {
            return identifierType.apply(identifier, subtype.getSerializedName());
        }

        protected Identifier getRegistryId(S subtype) {
            return Identifier.fromNamespaceAndPath(moduleId.getNamespace(), getRegistryName(subtype));
        }
    }
}
