export enum KeyCapSize {
  // Key Sizes
  ONE_U = 1,
  ONE_POINT_TWO_FIVE_U = 1.25,
  ONE_POINT_FIVE_U = 1.5,
  ONE_POINT_SEVEN_FIVE_U = 1.75,
  TWO_U = 2,
  TWO_POINT_TWO_FIVE_U = 2.25,
  TWO_POINT_SEVEN_FIVE_U = 2.75,
  SIX_POINT_TWO_FIZE_U = 6.25
}

export enum KeyCapOrientation {
  HORIZONTAL,
  VERTICAL
}

export interface KeyCap {
  size: KeyCapSize;
  orientation: KeyCapOrientation;
  topLabel?: string;
  bottomLabel?: string;
}

export interface Spacer {
  height: number;
  width: number;
}

export const KEY_CAP_SIZE = 64;
export type Key = KeyCap | Spacer;