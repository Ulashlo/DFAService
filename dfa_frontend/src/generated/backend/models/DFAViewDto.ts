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
/**
 * Info for presenting dfa.
 * @export
 * @interface DFAViewDTO
 */
export interface DFAViewDTO {
    /**
     * Dfa address.
     * @type {string}
     * @memberof DFAViewDTO
     */
    address: string;
    /**
     * Dfa owner address.
     * @type {string}
     * @memberof DFAViewDTO
     */
    owner: string;
    /**
     * Dfa name.
     * @type {string}
     * @memberof DFAViewDTO
     */
    name: string;
    /**
     * Dfa symbol.
     * @type {string}
     * @memberof DFAViewDTO
     */
    symbol: string;
    /**
     * Initial supply for dfa.
     * @type {number}
     * @memberof DFAViewDTO
     */
    totalSupply: number;
}

export function DFAViewDTOFromJSON(json: any): DFAViewDTO {
    return DFAViewDTOFromJSONTyped(json, false);
}

export function DFAViewDTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): DFAViewDTO {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'address': json['address'],
        'owner': json['owner'],
        'name': json['name'],
        'symbol': json['symbol'],
        'totalSupply': json['totalSupply'],
    };
}

export function DFAViewDTOToJSON(value?: DFAViewDTO | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'address': value.address,
        'owner': value.owner,
        'name': value.name,
        'symbol': value.symbol,
        'totalSupply': value.totalSupply,
    };
}


