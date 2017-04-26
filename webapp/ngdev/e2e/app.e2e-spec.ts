import { XpnsrPage } from './app.po';

describe('xpnsr App', () => {
  let page: XpnsrPage;

  beforeEach(() => {
    page = new XpnsrPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
