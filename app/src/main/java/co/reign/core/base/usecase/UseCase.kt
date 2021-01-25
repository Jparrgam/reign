package co.reign.core.base.usecase

import kotlinx.coroutines.flow.Flow

abstract class UseCase<Params, Response> {
    abstract fun perform(params: Params): Flow<Response>
}