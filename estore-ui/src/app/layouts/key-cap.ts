export enum KeySize {
  ONE_U,
  ONE_POINT_TWO_FIVE_U,
  ONE_POINT_FIVE_U,
  ONE_POINT_SEVEN_FIVE_U,
  TWO_U,
  TWO_POINT_TWO_FIVE_U,
  TWO_POINT_SEVEN_FIVE_U,
  SIX_POINT_TWO_FIZE_U
}

export enum KeyOrientation {
  HORIZONTAL,
  VERTICAL
}

export interface Key {
  size: KeySize;
  orientation: KeyOrientation;
  topLabel?: string;
  bottomLabel?: string;
}

export interface Space {
  size: KeySize | number; 
}

export type KeyCap = Key | Space;