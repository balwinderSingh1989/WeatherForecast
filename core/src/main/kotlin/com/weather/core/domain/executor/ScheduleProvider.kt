package com.weather.core.domain.executor

import io.reactivex.Scheduler

interface ScheduleProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}