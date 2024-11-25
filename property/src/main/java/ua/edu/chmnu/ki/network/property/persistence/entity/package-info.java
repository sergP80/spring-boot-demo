@GenericGenerators({
        @GenericGenerator(
                name = "PROPERTY_GENERATOR",
                strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                parameters = {
                        @Parameter(name = "sequence_name", value = "property.properties_id_seq")
                }
        )
})
package ua.edu.chmnu.ki.network.property.persistence.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import org.hibernate.annotations.Parameter;