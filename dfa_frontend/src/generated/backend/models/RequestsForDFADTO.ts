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

import { exists, mapValues } from '../runtime';
import {
    ExchangeInfo,
    ExchangeInfoFromJSON,
    ExchangeInfoFromJSONTyped,
    ExchangeInfoToJSON,
} from './';

/**
 * All requests for one DFA.
 * @export
 * @interface RequestsForDFADTO
 */
export interface RequestsForDFADTO {
    /**
     * Dfa to get address.
     * @type {string}
     * @memberof RequestsForDFADTO
     */
    dfaToGetAddress: string;
    /**
     * Exchange infos.
     * @type {Array<ExchangeInfo>}
     * @memberof RequestsForDFADTO
     */
    exchangeInfos: Array<ExchangeInfo>;
}

export function RequestsForDFADTOFromJSON(json: any): RequestsForDFADTO {
    return RequestsForDFADTOFromJSONTyped(json, false);
}

export function RequestsForDFADTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): RequestsForDFADTO {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'dfaToGetAddress': json['dfaToGetAddress'],
        'exchangeInfos': ((json['exchangeInfos'] as Array<any>).map(ExchangeInfoFromJSON)),
    };
}

export function RequestsForDFADTOToJSON(value?: RequestsForDFADTO | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'dfaToGetAddress': value.dfaToGetAddress,
        'exchangeInfos': ((value.exchangeInfos as Array<any>).map(ExchangeInfoToJSON)),
    };
}


