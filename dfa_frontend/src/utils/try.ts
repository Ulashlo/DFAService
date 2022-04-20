export class Try<T> {
  private readonly supplier: () => T;

  private constructor(supplier: () => T) {
    this.supplier = supplier;
  }

  // eslint-disable-next-line no-shadow
  public static of<T>(supplier: () => T): Try<T> {
    return new Try<T>(supplier);
  }

  public get(): T {
    return this.supplier();
  }

  public orElse(defaultValue: T): T {
    try {
      return this.get();
    } catch {
      return defaultValue;
    }
  }

  public map<R>(func: (val: T) => R): Try<R> {
    return new Try<R>(() => func(this.supplier()));
  }
}
