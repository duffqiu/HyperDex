// Copyright (c) 2012, Robert Escriva
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
//     * Redistributions of source code must retain the above copyright notice,
//       this list of conditions and the following disclaimer.
//     * Redistributions in binary form must reproduce the above copyright
//       notice, this list of conditions and the following disclaimer in the
//       documentation and/or other materials provided with the distribution.
//     * Neither the name of Replicant nor the names of its contributors may be
//       used to endorse or promote products derived from this software without
//       specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

#ifndef hyperdex_tools_util_h_
#define hyperdex_tools_util_h_

// POSIX
#include <sys/stat.h>

// po6
#include <po6/pathname.h>

// e
#include <e/popt.h>

// HyperDex
#include "namespace.h"

BEGIN_HYPERDEX_NAMESPACE

class connect_opts
{
    public:
        connect_opts()
            : m_ap() , m_host("127.0.0.1") , m_port(1982)
        {
            m_ap.arg().name('h', "host")
                      .description("connect to the coordinator on an IP address or hostname (default: 127.0.0.1)")
                      .metavar("addr").as_string(&m_host);
            m_ap.arg().name('p', "port")
                      .description("connect to the coordinator on an alternative port (default: 1982)")
                      .metavar("port").as_long(&m_port);
        }
        ~connect_opts() throw () {}

    public:
        const e::argparser& parser() { return m_ap; }
        const char* host() { return m_host; }
        uint16_t port() { return m_port; }
        bool validate()
        {
            if (m_port <= 0 || m_port >= (1 << 16))
            {
                std::cerr << "port number to connect to is out of range" << std::endl;
                return false;
            }

            return true;
        }

        private:
            connect_opts(const connect_opts&);
            connect_opts& operator = (const connect_opts&);

    private:
        e::argparser m_ap;
        const char* m_host;
        long m_port;
};

#ifdef HYPERDEX_EXEC_DIR
#define HYPERDEX_LIB_NAME "libhyperdex-coordinator"
#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wlarger-than="
inline bool
locate_coordinator_lib(const char* argv0, po6::pathname* path)
{
    // find the right library
    std::vector<po6::pathname> paths;
    const char* env = getenv("HYPERDEX_COORD_LIB");
    static const char* exts[] = { "", ".so.0.0.0", ".so.0", ".so", ".dylib", 0 };

    for (size_t i = 0; exts[i]; ++i)
    {
        std::string base(HYPERDEX_LIB_NAME);
        base += exts[i];
        paths.push_back(po6::join(HYPERDEX_EXEC_DIR, base));
        paths.push_back(po6::join(po6::pathname(argv0).dirname(),
                                  po6::join(".libs", base)));

        if (env)
        {
            std::string envlib(env);
            envlib += exts[i];
            paths.push_back(envlib);
        }
    }

    // maybe we're running out of Git.  make it "just work"
    char selfbuf[PATH_MAX + 1];
    memset(selfbuf, 0, sizeof(selfbuf));

    if (readlink("/proc/self/exe", selfbuf, PATH_MAX) >= 0)
    {
        po6::pathname workdir(selfbuf);
        workdir = workdir.dirname();
        po6::pathname gitdir(po6::join(workdir, ".git"));
        struct stat buf;

        if (stat(gitdir.get(), &buf) == 0 &&
            S_ISDIR(buf.st_mode))
        {
            po6::pathname libdir(po6::join(workdir, ".libs"));

            for (size_t i = 0; exts[i]; ++i)
            {
                std::string libname(HYPERDEX_LIB_NAME);
                libname += exts[i];
                paths.push_back(po6::join(libdir, libname));
            }
        }
    }

    size_t idx = 0;

    while (idx < paths.size())
    {
        struct stat buf;

        if (stat(paths[idx].get(), &buf) == 0)
        {
            *path = paths[idx];
            return true;
        }

        ++idx;
    }

    return false;
}
#pragma GCC diagnostic pop
#undef HYPERDEX_LIB_NAME
#endif // HYPERDEX_EXEC_DIR

END_HYPERDEX_NAMESPACE

#endif // hyperdex_tools_util_h_
