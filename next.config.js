/** @type {import('next').NextConfig} */
module.exports = {
  reactStrictMode: true,
  images: {
    unoptimized: true,
  },
  basePath: process.env.GITHUB_ACTIONS ? '/mrt-buddy-web' : '',
  assetPrefix: process.env.GITHUB_ACTIONS ? '/mrt-buddy-web/' : '',
  trailingSlash: true,
  async rewrites() {
    return [
      {
        source: '/privacy-poilcy.html',
        destination: '/privacy-policy',
      },
      {
        source: '/privacy-policy.html',
        destination: '/privacy-policy',
      },
      {
        source: '/contributors.html',
        destination: '/contributors',
      },
    ];
  },
}