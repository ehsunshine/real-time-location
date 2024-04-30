package com.example.realtimelocation.data.tabs

import org.koin.core.scope.Scope
import org.koin.dsl.module

private class BottomTabsRepository {
    fun getTabs() = listOf(
        BottomTabs.Home,
        BottomTabs.Map,
    )
}

fun interface GetBottomTabs {
    operator fun invoke(): List<BottomTabs>
}

val bottomTabsDataModule = module {
    single { BottomTabsRepository() }

    factory<GetBottomTabs> { GetBottomTabs(bottomTabsRepository::getTabs) }
}
private val Scope.bottomTabsRepository: BottomTabsRepository get() = get<BottomTabsRepository>()