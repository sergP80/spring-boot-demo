@GenericGenerators({
        @GenericGenerator(
                name = "HOTEL_GENERATOR",
                strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                parameters = {
                        @Parameter(name = "sequence_name", value = "core.hotel_id_seq")
                }
        )
})
package ua.edu.chmnu.ki.network.hotel.persistence.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import org.hibernate.annotations.Parameter;