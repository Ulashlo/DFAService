/* tslint:disable */
/* eslint-disable */
/**
 * App API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: snapshot
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import * as runtime from '../runtime';
import {
    ApiError,
    ApiErrorFromJSON,
    ApiErrorToJSON,
    DFAInfoForCreateDTO,
    DFAInfoForCreateDTOFromJSON,
    DFAInfoForCreateDTOToJSON,
    DFAViewDto,
    DFAViewDtoFromJSON,
    DFAViewDtoToJSON,
} from '../models';

export interface CreateDFARequest {
    dFAInfoForCreateDTO: DFAInfoForCreateDTO;
}

export interface GetBalanceRequest {
    dfaAddress: string;
}

/**
 * DfaControllerApi - interface
 * 
 * @export
 * @interface DfaControllerApiInterface
 */
export interface DfaControllerApiInterface {
    /**
     * 
     * @summary Create new dfa.
     * @param {DFAInfoForCreateDTO} dFAInfoForCreateDTO 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof DfaControllerApiInterface
     */
    createDFARaw(requestParameters: CreateDFARequest): Promise<runtime.ApiResponse<void>>;

    /**
     * Create new dfa.
     */
    createDFA(requestParameters: CreateDFARequest): Promise<void>;

    /**
     * 
     * @summary Return all existing in system dfa.
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof DfaControllerApiInterface
     */
    getAllDfaRaw(): Promise<runtime.ApiResponse<Array<DFAViewDto>>>;

    /**
     * Return all existing in system dfa.
     */
    getAllDfa(): Promise<Array<DFAViewDto>>;

    /**
     * 
     * @summary Return amount of dfa.
     * @param {string} dfaAddress Address of dfa.
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof DfaControllerApiInterface
     */
    getBalanceRaw(requestParameters: GetBalanceRequest): Promise<runtime.ApiResponse<number>>;

    /**
     * Return amount of dfa.
     */
    getBalance(requestParameters: GetBalanceRequest): Promise<number>;

}

/**
 * 
 */
export class DfaControllerApi extends runtime.BaseAPI implements DfaControllerApiInterface {

    /**
     * Create new dfa.
     */
    async createDFARaw(requestParameters: CreateDFARequest): Promise<runtime.ApiResponse<void>> {
        if (requestParameters.dFAInfoForCreateDTO === null || requestParameters.dFAInfoForCreateDTO === undefined) {
            throw new runtime.RequiredError('dFAInfoForCreateDTO','Required parameter requestParameters.dFAInfoForCreateDTO was null or undefined when calling createDFA.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        if (this.configuration && this.configuration.accessToken) {
            const token = this.configuration.accessToken;
            const tokenString = typeof token === 'function' ? token("bearer-jwt", []) : token;

            if (tokenString) {
                headerParameters["Authorization"] = `Bearer ${tokenString}`;
            }
        }
        const response = await this.request({
            path: `/safe/dfa`,
            method: 'PUT',
            headers: headerParameters,
            query: queryParameters,
            body: DFAInfoForCreateDTOToJSON(requestParameters.dFAInfoForCreateDTO),
        });

        return new runtime.VoidApiResponse(response);
    }

    /**
     * Create new dfa.
     */
    async createDFA(requestParameters: CreateDFARequest): Promise<void> {
        await this.createDFARaw(requestParameters);
    }

    /**
     * Return all existing in system dfa.
     */
    async getAllDfaRaw(): Promise<runtime.ApiResponse<Array<DFAViewDto>>> {
        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        if (this.configuration && this.configuration.accessToken) {
            const token = this.configuration.accessToken;
            const tokenString = typeof token === 'function' ? token("bearer-jwt", []) : token;

            if (tokenString) {
                headerParameters["Authorization"] = `Bearer ${tokenString}`;
            }
        }
        const response = await this.request({
            path: `/safe/dfa`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        });

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(DFAViewDtoFromJSON));
    }

    /**
     * Return all existing in system dfa.
     */
    async getAllDfa(): Promise<Array<DFAViewDto>> {
        const response = await this.getAllDfaRaw();
        return await response.value();
    }

    /**
     * Return amount of dfa.
     */
    async getBalanceRaw(requestParameters: GetBalanceRequest): Promise<runtime.ApiResponse<number>> {
        if (requestParameters.dfaAddress === null || requestParameters.dfaAddress === undefined) {
            throw new runtime.RequiredError('dfaAddress','Required parameter requestParameters.dfaAddress was null or undefined when calling getBalance.');
        }

        const queryParameters: any = {};

        if (requestParameters.dfaAddress !== undefined) {
            queryParameters['dfaAddress'] = requestParameters.dfaAddress;
        }

        const headerParameters: runtime.HTTPHeaders = {};

        if (this.configuration && this.configuration.accessToken) {
            const token = this.configuration.accessToken;
            const tokenString = typeof token === 'function' ? token("bearer-jwt", []) : token;

            if (tokenString) {
                headerParameters["Authorization"] = `Bearer ${tokenString}`;
            }
        }
        const response = await this.request({
            path: `/safe/dfa/balance`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        });

        return new runtime.TextApiResponse(response) as any;
    }

    /**
     * Return amount of dfa.
     */
    async getBalance(requestParameters: GetBalanceRequest): Promise<number> {
        const response = await this.getBalanceRaw(requestParameters);
        return await response.value();
    }

}
