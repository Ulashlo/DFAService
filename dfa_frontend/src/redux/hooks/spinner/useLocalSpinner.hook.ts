export async function useLocalSpinner<T>(
  asyncFunc: () => Promise<T>,
  params: { showSpinner: () => void; hideSpinner: () => void },
): Promise<T> {
  params.showSpinner();
  try {
    return await asyncFunc();
  } finally {
    params.hideSpinner();
  }
}
