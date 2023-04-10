export enum CustomKeyboardSize {
  ONE_HUNDRED = "ONE_HUNDRED",
  EIGHTY = "EIGHTY",
  SIXTY = "SIXTY"
} 

export enum CustomKeyboardSwitchType {
  GATERON_BLACK = "GATERON_BLACK",
  CHERRY_MX_BLACK = "CHERRY_MX_BLACK",
  GATERON_BLUE = "GATERON_BLUE",
  CHERRY_MX_BLUE = "CHERRY_MX_BLUE",
  GATERON_BROWN = "GATERON_BROWN",
  CHERRY_MX_BROWN = "CHERRY_MX_BLUE",
  GATERON_CLEAR = "GATERON_CLEAR",
  CHERRY_MX_CLEAR = "CHERRY_MX_CLEAR",
  GATERON_GREEN = "GATERON_GREEN",
  CHERRY_MX_GREEN = "CHERRY_MX_GREEN",
  GATERON_RED = "GATERON_RED",
  CHERRY_MX_RED = "CHERRY_MX_RED"
} 

export enum PricePerSwitchType {
  GATERON = 0.27,
  CHERRY_MX = 0.40
}

export interface CustomKeyboard {
  size: CustomKeyboardSize;
  price: number;
  caseColor: string;
  keycapColor: string;
  labelColor: string;
  switchType: CustomKeyboardSwitchType;
}
