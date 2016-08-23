package com.teanlime.domain.api.usecase;

/**
 * Channel of communication between domain and data objects.
 * Can be handled both synchronously and asynchronously
 *
 * @param <M> successful response type
 * @param <E> error response type
 */
public interface UseCase<M, E> {

    /**
     * A command to start execution/processing of the use case
     * Computation results will be populated to the given callback
     *
     * @param callback callback of the response
     */
    void execute(UseCaseCallback<M, E> callback);

    /**
     * If the use case computation is running, this method will cancel this operation
     */
    void cancel();
}