package com.victorkirui.networq

import com.victorkirui.add_event.addEventModule
import com.victorkirui.cards.cardModule
import com.victorkirui.data.dataModule
import com.victorkirui.domain.domainModule
import com.victorkirui.local.localModule
import org.koin.dsl.module

val appModule = module{includes(localModule, dataModule, domainModule, addEventModule, cardModule)}