import { FrontendWebPage } from './app.po';

describe('frontend-web App', function() {
  let page: FrontendWebPage;

  beforeEach(() => {
    page = new FrontendWebPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
