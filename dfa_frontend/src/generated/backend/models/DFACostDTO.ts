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
 * Info about dfa cost.
 * @export
 * @interface DFACostDTO
 */
export interface DFACostDTO {
    /**
     * Dfa address.
     * @type {string}
     * @memberof DFACostDTO
     */
    address: string;
    /**
     * Dfas cost.
     * @type {number}
     * @memberof DFACostDTO
     */
    cost: number;
}

export function DFACostDTOFromJSON(json: any): DFACostDTO {
    return DFACostDTOFromJSONTyped(json, false);
}

export function DFACostDTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): DFACostDTO {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'address': json['address'],
        'cost': json['cost'],
    };
}

export function DFACostDTOToJSON(value?: DFACostDTO | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'address': value.address,
        'cost': value.cost,
    };
}


