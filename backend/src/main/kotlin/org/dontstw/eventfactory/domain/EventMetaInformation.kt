package org.dontstw.eventfactory.domain

import org.dontstw.eventfactory.const.EventStatus
import java.time.LocalDateTime

data class EventMetaInformation(
        var eventNo: Long,
        var eventOrganizer: String,
        var eventStartTime: LocalDateTime,
        var eventCreationTime: LocalDateTime,
        var saveStatus: EventStatus
)
