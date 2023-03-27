import { NotNullOrEmptyPipe } from './not-null-or-empty.pipe';

describe('NotNullOrEmptyPipe', () => {
  it('create an instance', () => {
    const pipe = new NotNullOrEmptyPipe();
    expect(pipe).toBeTruthy();
  });
});
